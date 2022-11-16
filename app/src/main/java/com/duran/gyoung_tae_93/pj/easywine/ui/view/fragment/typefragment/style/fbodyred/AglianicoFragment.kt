package com.duran.gyoung_tae_93.pj.easywine.ui.view.fragment.typefragment.style.fbodyred

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.duran.gyoung_tae_93.pj.easywine.R
import com.duran.gyoung_tae_93.pj.easywine.data.model.type.style.StyleModel
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentAglianicoBinding
import com.duran.gyoung_tae_93.pj.easywine.databinding.FragmentZinfandelBinding
import com.duran.gyoung_tae_93.pj.easywine.util.FBAuth
import com.duran.gyoung_tae_93.pj.easywine.util.FBDocRef
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AglianicoFragment : Fragment() {

    private lateinit var binding: FragmentAglianicoBinding

    private var isFabOpen = false // Fab 버튼 default는 닫힘
    lateinit var style: StyleModel
    var noteId = ""

    var topType: String? = null
    var item1: String? = null
    var item2: String? = null
    var item3: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_aglianico, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fg = this

        item1 = arguments?.getString("item1")
        item2 = arguments?.getString("item2")
        item3 = arguments?.getString("item3")

        setTopType()
        setFabButton()
    }

    /**
     *  상단 Type text
     */
    private fun setTopType() {
        topType = "$item1  >  $item2  >  $item3"
    }

    /**
     *  Fab 버튼 setting
     */
    private fun setFabButton() {
        binding.fabMain.setOnClickListener {
            toggleFab()
        }

        binding.fabHome.setOnClickListener {
            it.findNavController().navigate(R.id.action_aglianicoFragment_to_fragment_type)
        }

        binding.fabFavorite.setOnClickListener {
            setFavoriteClick()
        }
    }

    private fun toggleFab() {
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (isFabOpen) {
            ObjectAnimator.ofFloat(binding.fabFavorite, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabHome, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 45f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(binding.fabFavorite, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabHome, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(binding.fabMain, View.ROTATION, 0f, 45f).apply { start() }
        }

        isFabOpen = !isFabOpen

    }

    /**
     *  즐겨찾기 버튼 클릭 시 Event
     */
    private fun setFavoriteClick() {
        // 즐겨찾기가 등록되있는지 조회
        FBDocRef.fbDB.collection("wine_type").document(FBAuth.getUid())
            .collection("style").whereEqualTo("wineTitle1", item3.toString()).get()
            .addOnSuccessListener { result ->

                if (result.documentChanges.isEmpty()) {
                    getSaveStyle()
                } else {
                    getStyleDocId()
                }
            }
    }

    // 즐겨찾기에 해당 와인이 존재 -> DocumentId를 찾아서 삭제
    private fun getStyleDocId() {
        FBDocRef.fbDB.collection("wine_type").document(FBAuth.getUid())
            .collection("style").whereEqualTo("wineTitle1", item3.toString()).get()
            .addOnSuccessListener { result ->

                for (item in result.documentChanges) noteId = item.document.id

                getDeleteStyle(noteId)
            }
    }

    // 찾은 DocumentId 문서 삭제
    private fun getDeleteStyle(noteId: String) {
        FBDocRef.fbDB.collection("wine_type").document(FBAuth.getUid())
            .collection("style").document(noteId).delete()

        Toast.makeText(context, "즐겨찾기를 삭제했습니다.", Toast.LENGTH_SHORT).show()
    }

    // 즐겨찾기 추가
    private fun getSaveStyle() {
        val title = item3.toString()
        val aroma = binding.tvWineAroma.text.toString()
        val ratingFlavor = binding.wineRatingFlavor.rating
        val ratingBody = binding.wineRatingBody.rating
        val ratingSweet = binding.wineRatingSweet.rating
        val ratingAcidity = binding.wineRatingAcidity.rating
        val ratingAlcohol = binding.wineRatingAlcohol.rating
        val saveTime = System.currentTimeMillis()

        CoroutineScope(Dispatchers.IO).launch {
            val wineType = StyleModel(
                FBAuth.getUid(),
                title,
                aroma,
                ratingFlavor,
                ratingBody,
                ratingSweet,
                ratingAcidity,
                ratingAlcohol,
                saveTime,
                true
            )
            FBDocRef.fbDB.collection("wine_type").document(FBAuth.getUid())
                .collection("style").document().set(wineType)
        }

        Toast.makeText(context, "즐겨찾기에 추가되었습니다.", Toast.LENGTH_SHORT).show()

    }

}