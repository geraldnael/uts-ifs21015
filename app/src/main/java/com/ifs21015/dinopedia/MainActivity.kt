package com.ifs21015.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21015.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataDinos = ArrayList<Dino>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDinos.setHasFixedSize(false)
        dataDinos.addAll(getListDinos())
        showRecyclerList()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, About::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    @SuppressLint("Recycle")
    private fun getListDinos(): ArrayList<Dino> {
        val dataName =
            resources.getStringArray(R.array.dinos_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.dinos_icon)
        val dataDescription =
            resources.getStringArray(R.array.dinos_description)
        val dataCharacteristic =
            resources.getStringArray(R.array.dinos_characteristic)
        val dataHabitat =
            resources.getStringArray(R.array.dinos_habitat)
        val dataProcess =
            resources.getStringArray(R.array.dino_process)
        val dataFood =
            resources.getStringArray(R.array.dinos_food)
        val dataLong =
            resources.getStringArray(R.array.dinos_long)
        val dataWeight =
            resources.getStringArray(R.array.dinos_weight)
        val dataWeakness =
            resources.getStringArray(R.array.dinos_weakness)
        val listDino = ArrayList<Dino>()
        for (i in dataName.indices) {
            val dino = Dino(
                dataName[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataCharacteristic[i],
                dataHabitat[i],
                dataProcess[i],
                dataFood[i],
                dataLong[i],
                dataWeight[i],
                dataWeakness[i]
            )
            listDino.add(dino)
        }
        return listDino
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvDinos.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvDinos.layoutManager =
                LinearLayoutManager(this)
        }
        val listDinoAdapter = ListDinoAdapter(dataDinos)
        binding.rvDinos.adapter = listDinoAdapter
        listDinoAdapter.setOnItemClickCallback(object :
            ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }
    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }
}