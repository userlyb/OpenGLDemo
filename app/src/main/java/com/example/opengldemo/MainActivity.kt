package com.example.opengldemo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.opengldemo.ui.theme.OpenGLDemoTheme
import kotlin.jvm.java
import android.widget.Toast

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OpenGLDemoTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        setContentView(R.layout.activity_main)

        val btnTest = findViewById<View>(R.id.SimpleTriangle)
        btnTest.setOnClickListener {
            Toast.makeText(this, "按钮被点击了！", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, SimpleRenderActivity::class.java)
//            intent.putExtra("type", 1)
//            startActivity(intent)
            startActivity(Intent(this, OpenGLPlayerActivity::class.java))
        }
    }

//    private fun requestPermission() {
//        val permissions = Permission.Group.STORAGE
//        AndPermission.with(this)
//            .runtime()
//            .permission(permissions)
//            .onGranted {
//            }
//            .onDenied {
//                Toast.makeText(this, "请打开权限，否则无法获取本地文件", Toast.LENGTH_SHORT).show()
//            }
//            .start()
//    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OpenGLDemoTheme {
        Greeting("Android")
    }
}