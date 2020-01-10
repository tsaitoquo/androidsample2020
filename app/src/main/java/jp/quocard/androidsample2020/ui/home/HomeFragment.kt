package jp.quocard.androidsample2020.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import jp.quocard.androidsample2020.MainActivity
import jp.quocard.androidsample2020.R
import jp.quocard.androidsample2020.databinding.FragmentHomeBinding
import jp.quocard.androidsample2020.domain.Project
import jp.sample.tsutou.githubClient.view.adapter.ProjectAdapter

class HomeFragment : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(HomeViewModel::class.java) }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var projectAdapter: ProjectAdapter

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED) && activity is MainActivity) {
                Toast.makeText(activity, "${project.full_name} clicked", Toast.LENGTH_SHORT).show()
//                (activity as MainActivity).show(project)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //dataBinding用のレイアウトリソース
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        projectAdapter = ProjectAdapter(projectClickCallback)

        binding.apply {
            projectList.adapter = projectAdapter
            isLoading = true
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewModel(viewModel)
    }


    //observe開始
    private fun observeViewModel(viewModel: HomeViewModel) {

        //データをSTARTED かRESUMED状態である場合にのみ、アップデートするように、LifecycleOwnerを紐付け、ライフサイクル内にオブザーバを追加
        viewModel.projectListLiveData.observe(viewLifecycleOwner, Observer { projects ->
            if (projects != null) {
                binding.isLoading = false
                projectAdapter.setProjectList(projects)
            }
        })
    }
}