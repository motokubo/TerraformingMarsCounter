package com.game.terraformingMars

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
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
import com.game.terraformingMars.ui.theme.TerraformingMarsPanelTheme

class MainActivity : ComponentActivity() {

    private var money = 0
    // Adicionar outras variáveis para recursos aqui

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Mapear os nomes dos recursos
        val resourcesMap = mapOf(
            R.id.resource_credits to "Megacréditos",
            R.id.resource_plants to "Plantas",
            R.id.resource_steel to "Aço",
            R.id.resource_titanium to "Titânio",
            R.id.resource_energy to "Energia",
            R.id.resource_heat to "Calor"
        )

        // Configurar os nomes dos recursos dinamicamente
        for ((id, name) in resourcesMap) {
            val resourceView = findViewById<LinearLayout>(id)
            val resourceNameTextView = resourceView.findViewById<TextView>(R.id.tv_resource_name)
            resourceNameTextView.text = name
        }

        for ((id, _) in resourcesMap) {
            val resourceView = findViewById<LinearLayout>(id)
            val decreaseQuantityButton = resourceView.findViewById<Button>(R.id.btn_decrease_quantity)
            val increaseQuantityButton = resourceView.findViewById<Button>(R.id.btn_increase_quantity)
            val decreaseProductionButton = resourceView.findViewById<Button>(R.id.btn_decrease_production)
            val increaseProductionButton = resourceView.findViewById<Button>(R.id.btn_increase_production)
            val quantityTextView = resourceView.findViewById<TextView>(R.id.tv_resource_quantity)
            val productionTextView = resourceView.findViewById<TextView>(R.id.tv_resource_production)

            decreaseQuantityButton.setOnClickListener {
                val currentQuantity = quantityTextView.text.toString().toInt()
                if (currentQuantity > 0) {
                    quantityTextView.text = (currentQuantity - 1).toString()
                }
            }

            increaseQuantityButton.setOnClickListener {
                val currentQuantity = quantityTextView.text.toString().toInt()
                quantityTextView.text = (currentQuantity + 1).toString()
            }

            decreaseProductionButton.setOnClickListener {
                val currentProduction = productionTextView.text.toString().toInt()
                if (currentProduction > 0) {
                    productionTextView.text = (currentProduction - 1).toString()
                }
            }

            increaseProductionButton.setOnClickListener {
                val currentProduction = productionTextView.text.toString().toInt()
                productionTextView.text = (currentProduction + 1).toString()
            }
        }

        val productionTurnButton = findViewById<Button>(R.id.btn_production_turn)
        productionTurnButton.setOnClickListener {
            // Adicionar a quantidade de energia para calor e zerar a energia
            val energyView = findViewById<LinearLayout>(R.id.resource_energy)
            val heatView = findViewById<LinearLayout>(R.id.resource_heat)
            val energyQuantityTextView = energyView.findViewById<TextView>(R.id.tv_resource_quantity)
            val heatQuantityTextView = heatView.findViewById<TextView>(R.id.tv_resource_quantity)

            val currentEnergyQuantity = energyQuantityTextView.text.toString().toInt()
            val currentHeatQuantity = heatQuantityTextView.text.toString().toInt()

            val newHeatQuantity = currentHeatQuantity + currentEnergyQuantity
            heatQuantityTextView.text = newHeatQuantity.toString()
            energyQuantityTextView.text = "0"

            // Adicionar os valores de produção para quantidade
            for ((id, _) in resourcesMap) {
                val resourceView = findViewById<LinearLayout>(id)
                val quantityTextView = resourceView.findViewById<TextView>(R.id.tv_resource_quantity)
                val productionTextView = resourceView.findViewById<TextView>(R.id.tv_resource_production)

                val currentQuantity = quantityTextView.text.toString().toInt()
                val productionValue = productionTextView.text.toString().toInt()

                val newQuantity = currentQuantity + productionValue
                quantityTextView.text = newQuantity.toString()
            }
        }
    }
    
}