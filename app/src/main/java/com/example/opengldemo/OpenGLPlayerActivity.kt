package com.example.opengldemo

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import android.view.Surface
import com.example.opengldemo.media.decoder.AudioDecoder
import com.example.opengldemo.media.decoder.VideoDecoder
import com.example.opengldemo.opengl.SimpleRender
import com.example.opengldemo.opengl.drawer.IDrawer
import com.example.opengldemo.opengl.drawer.VideoDrawer
import java.io.File
//import kotlinx.android.synthetic.main.activity_opengl_player.*
import java.util.concurrent.Executors


/**
 * 使用OpenGL渲染的播放器
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @since LearningVideo
 * @version LearningVideo
 * @Datetime 2019-10-26 21:07
 *
 */
class OpenGLPlayerActivity: ComponentActivity() {
    lateinit var drawer: IDrawer
    private lateinit var gl_surface: GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opengl_player)
        initRender()
    }

    private fun initRender() {
        drawer = VideoDrawer()
        drawer.setVideoSize(1920, 1080)
        drawer.getSurfaceTexture {
            initPlayer(Surface(it))
        }
        gl_surface = findViewById<GLSurfaceView>(R.id.gl_surface)
        gl_surface.setEGLContextClientVersion(2)
        val render = SimpleRender()
        render.addDrawer(drawer)
        gl_surface.setRenderer(render)
    }

    private fun initPlayer(sf: Surface) {
        val threadPool = Executors.newFixedThreadPool(10)

        val mCacheDir = externalCacheDir
        val videoFile = File(mCacheDir, "ygzzfyh.mp4");
        val path = videoFile.absolutePath
        Log.i("debuglog", "debuglog path: "+path);
        val videoDecoder = VideoDecoder(path, null, sf)
        threadPool.execute(videoDecoder)

        val audioDecoder = AudioDecoder(path)
        threadPool.execute(audioDecoder)

        videoDecoder.goOn()
        audioDecoder.goOn()
    }
}