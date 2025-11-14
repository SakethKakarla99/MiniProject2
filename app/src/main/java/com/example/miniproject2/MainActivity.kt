package com.example.miniproject2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.miniproject2.ui.theme.MiniProject2Theme // <-- check this name
import com.example.miniproject2.ui.theme.UserDirectoryScreen
import com.example.miniproject2.ui.theme.UserViewModel
import com.example.miniproject2.ui.theme.UserViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewModel: UserViewModel by viewModels {
        UserViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // If your theme function has a different name, use that instead
            MiniProject2Theme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    UserDirectoryScreen(viewModel = viewModel)
                }
            }
        }
    }
}
