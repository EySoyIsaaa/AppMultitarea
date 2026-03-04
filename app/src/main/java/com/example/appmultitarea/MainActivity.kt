package com.example.appmultitarea

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.appmultitarea.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var taskAJob: Job? = null
    private var taskBJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIniciar.setOnClickListener { startMultitaskDemo() }
        binding.btnCancelar.setOnClickListener { cancelTasks() }
    }

    private fun startMultitaskDemo() {
        cancelTasks(showCancelMessage = false)
        resetUiForStart()

        taskAJob = lifecycleScope.launch {
            for (i in 1..10) {
                delay(350)
                val progress = i * 10
                binding.progressA.progress = progress
                val dots = ".".repeat((i % 3) + 1)
                binding.txtEstadoA.text = getString(R.string.progreso_tarea_a_visible, progress, dots)
                appendLog(getString(R.string.log_tarea_a, progress))
            }
            binding.txtEstadoA.text = getString(R.string.tarea_a_terminada)
            appendLog(getString(R.string.log_fin_a))
            checkIfBothFinished()
        }

        taskBJob = lifecycleScope.launch {
            for (i in 1..5) {
                binding.txtEstadoB.text = getString(R.string.tarea_b_etapa, i)
                appendLog(getString(R.string.log_tarea_b_inicio, i))

                val result = withContext(Dispatchers.Default) {
                    heavyCalculation(45_000 + i * 1_500)
                }

                val progress = i * 20
                binding.progressB.progress = progress
                binding.txtResultadoB.text = getString(R.string.resultado_b, i, result)
                appendLog(getString(R.string.log_tarea_b_fin, i, progress))
                delay(500)
            }

            binding.txtEstadoB.text = getString(R.string.tarea_b_terminada)
            appendLog(getString(R.string.log_fin_b))
            checkIfBothFinished()
        }
    }

    private fun heavyCalculation(limit: Int): Long {
        var total = 0L
        for (n in 1..limit) {
            total += (n * n % 97)
        }
        return total
    }

    private fun cancelTasks(showCancelMessage: Boolean = true) {
        taskAJob?.cancel()
        taskBJob?.cancel()

        if (showCancelMessage) {
            binding.txtEstadoA.text = getString(R.string.tarea_cancelada)
            binding.txtEstadoB.text = getString(R.string.tarea_cancelada)
            binding.txtResumen.text = getString(R.string.resumen_cancelado)
            appendLog(getString(R.string.log_cancelado))
        }
    }

    private fun resetUiForStart() {
        binding.progressA.progress = 0
        binding.progressB.progress = 0
        binding.txtEstadoA.text = getString(R.string.iniciando_tarea_a)
        binding.txtEstadoB.text = getString(R.string.iniciando_tarea_b)
        binding.txtResultadoB.text = getString(R.string.resultado_inicial_b)
        binding.txtResumen.text = getString(R.string.demo_corriendo_visible)
        binding.txtLog.text = getString(R.string.log_inicial)
    }

    private fun appendLog(message: String) {
        binding.txtLog.append("\n• $message")
    }

    private fun checkIfBothFinished() {
        if (taskAJob?.isCompleted == true && taskBJob?.isCompleted == true) {
            binding.txtResumen.text = getString(R.string.resumen_finalizado_visible)
            appendLog(getString(R.string.log_final))
        }
    }
}
