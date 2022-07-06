package com.onedev.reqres.ui

import android.os.Bundle
import com.onedev.reqres.data.User
import com.onedev.reqres.databinding.ActivityMainBinding
import com.onedev.reqres.event.StateEventSubscriber
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ScopeActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.run {
            btnFetchUser.setOnClickListener {
                mainViewModel.getUsers(1)
            }
        }

        mainViewModel.subscribeUser(subscribeUser())
    }

    private fun subscribeUser() = object : StateEventSubscriber<List<User>> {
        override fun onIdle() {
            binding.tvUser.append("idle...\n")
        }

        override fun onLoading() {
            binding.tvUser.append("loading...\n")
        }

        override fun onFailure(throwable: Throwable) {
            binding.tvUser.append("${throwable.message}...\n")
        }

        override fun onSuccess(data: List<User>) {
            binding.tvUser.append("${data}...\n")
        }
    }
}