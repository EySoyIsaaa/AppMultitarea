package com.example.appmultitarea

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appmultitarea.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var taskAJob: Job? = null
    private var taskBJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIniciar.setOnClickListener {
            startMultitaskDemo()
        }

        binding.btnCancelar.setOnClickListener {
            taskAJob?.cancel()
            taskBJob?.cancel()
            binding.txtEstadoA.text = getString(R.string.tarea_cancelada)
            binding.txtEstadoB.text = getString(R.string.tarea_cancelada)
            binding.txtResumen.text = getString(R.string.resumen_cancelado)
        }
    }

    private fun startMultitaskDemo() {
        taskAJob?.cancel()
        taskBJob?.cancel()

        binding.progressA.progress = 0
        binding.progressB.progress = 0
        binding.txtEstadoA.text = getString(R.string.iniciando_tarea_a)
        binding.txtEstadoB.text = getString(R.string.iniciando_tarea_b)
        binding.txtResumen.text = getString(R.string.demo_corriendo)

        taskAJob = lifecycleScope.launch {
            for (i in 1..10) {
                delay(450)
                binding.progressA.progress = i * 10
                binding.txtEstadoA.text = getString(R.string.progreso_tarea_a, i * 10)
            }
            binding.txtEstadoA.text = getString(R.string.tarea_finalizada)
            checkIfBothFinished()
        }

        taskBJob = lifecycleScope.launch {
            for (i in 1..10) {
                delay(300)
                binding.progressB.progress = i * 10
                binding.txtEstadoB.text = getString(R.string.progreso_tarea_b, i * 10)
            }
            binding.txtEstadoB.text = getString(R.string.tarea_finalizada)
            checkIfBothFinished()
        }
    }

    private fun checkIfBothFinished() {
        val bothFinished =
            (taskAJob?.isCompleted == true) &&
                (taskBJob?.isCompleted == true)

        if (bothFinished) {
            binding.txtResumen.text = getString(R.string.resumen_finalizado)
        }
    }
}
