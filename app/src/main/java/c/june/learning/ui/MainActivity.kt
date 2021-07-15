package c.june.learning.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import c.june.learning.R
import c.june.learning.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val todoFragment by lazy { TodoFragment() }
    private val githubFragment by lazy { GithubFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomViewSelectedListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_notification -> {

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setBottomViewSelectedListener() {
        binding.bottomView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_github -> {
                    commitFragmentTransaction(githubFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_todo -> {
                    commitFragmentTransaction(todoFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
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