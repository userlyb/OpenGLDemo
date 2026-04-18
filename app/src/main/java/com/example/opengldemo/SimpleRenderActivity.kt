package com.example.opengldemo

import android.graphics.BitmapFactory
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.opengldemo.R.id
import com.example.opengldemo.opengl.SimpleRender
import com.example.opengldemo.opengl.drawer.IDrawer
import com.example.opengldemo.opengl.drawer.TriangleDrawer
import com.example.opengldemo.opengl.drawer.BitmapDrawer


//import kotlinx.android.synthetic.main.activity_simpler_render.*

class SimpleRenderActivity : ComponentActivity() {
    private lateinit var drawer: IDrawer
    private lateinit var gl_surface: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, "这是 SimpleRenderActivity", Toast.LENGTH_SHORT).show()
        setContentView(R.layout.activity_simpler_render)
        gl_surface = findViewById<GLSurfaceView>(R.id.gl_surface)

        drawer = if (intent.getIntExtra("type", 0) == 0) {
            TriangleDrawer()
        } else {
            BitmapDrawer(BitmapFactory.decodeResource(resources, R.drawable.cover))
        }
        initRender(drawer)
    }

    private fun initRender(drawer: IDrawer) {
        gl_surface.setEGLContextClientVersion(2)
        val render = SimpleRender()
        render.addDrawer(drawer)
        gl_surface.setRenderer(render)
    }

    override fun onDestroy() {
        drawer.release()
        super.onDestroy()
    }
}