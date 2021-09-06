package c.june.learning.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import c.june.learning.R
import c.june.learning.data.MainRepository
import c.june.learning.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val todoFragment by lazy { TodoFragment() }
    private val githubFragment by lazy { GithubFragment() }

    @Inject lateinit var mainRepository: MainRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomViewSelectedListener()
        getUserNotificationState()
    }

    private fun getUserNotificationState() {
        lifecycleScope.launch {
            mainRepository.getUserNotificationState().collect { state ->
                withContext(Dispatchers.Main) {
                    makeSnackBar(state)
                }
            }
        }
    }

    private fun makeSnackBar(state: Boolean) {
        Snackbar.make(binding.root, "알림이 현재 ${if (state) "ON" else "OFF"} 상태입니다.", Snackbar.LENGTH_LONG)
            .setAction(if (state) "OFF하기" else "ON하기") {
                lifecycleScope.launch {
                    mainRepository.saveUserNotificationState(!state)
                }
            }
            .show()
    }

    private fun setBottomViewSelectedListener() {
        binding.bottomView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_github -> {
                    commitFragmentTransaction(githubFragment)
                    true
                }
                R.id.menu_todo -> {
                    commitFragmentTransaction(todoFragment)
                    true
                }
                else -> {
                    false
                }
            }
        }

        binding.bottomView.selectedItemId = R.id.menu_github
    }

    private fun commitFragmentTransaction(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.container, fragment)
        }
    }
}