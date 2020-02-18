package com.example.hw2_pizzaorder

import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.os.Bundle as AndroidOsBundle

class MainActivity : AppCompatActivity() {

    var total_price = 0.0
    var cart_price = 0.0
    var size_price = 9.99 //default pizza is medium
    var topping_price = 0.0
    var delivery = 0.0

    override fun onCreate(savedInstanceState: AndroidOsBundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create a list of some strings that will be shown in the listview
        val pizzaList = listOf("Pepperoni", "Margharitta", "Hawaiian", "BBQ Chicken")

        // Create an adapter with 3 parameters: activity (this), layout, list
        val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pizzaList)

        // set the adapter to listview
        pizza_list.adapter = myAdapter

        // Registering setOnItemClickListener that listens item click events in the listview
        pizza_list.setOnItemClickListener { _, _, position, _ ->

            // Based on the index of position selected, set the corresponding image
            val imageId = when (position) {
                0 -> R.drawable.pepperoni
                1 -> R.drawable.margheritta
                2 -> R.drawable.hawaiian
                else -> R.drawable.bbq_chicken
            }
            pizza_pic.setImageResource(imageId)
        }
        updatePrice()

    }

    fun radioButtonClick(view:View) {
        // check which radio button was clicked
        when (view) {
            medium -> size_price = 9.99
            large -> size_price = 13.99
            else -> size_price = 15.99
        }
        updatePrice()
    }

    fun deliveryButtonClick(view: View) {
        if(delivery_switch.isChecked)
            delivery=2.00
        else
            delivery=0.00

        updatePrice()
    }

    fun toppingsButtonClick(view: View){
        topping_price=0.00
        if(checkBox.isChecked)
            topping_price+=1.69
        if(checkBox2.isChecked)
            topping_price+=1.69
        if(checkBox3.isChecked)
            topping_price+=1.69
        if(checkBox4.isChecked)
            topping_price+=1.69
        if(checkBox5.isChecked)
            topping_price+=1.69
        if(checkBox6.isChecked)
            topping_price+=1.69
        if(checkBox7.isChecked)
            topping_price+=1.69

        updatePrice()
    }

    fun addToCartButton(view: View){
        cart_price+=total_price
        cart_text.text=(String.format("Cart total: $%.2f", cart_price))
    }

    fun updatePrice()
    {
        total_price = size_price + topping_price + delivery
        //set to 2 decimal places so price doesn't look strange
        total_price_box.text = (String.format("Total Price: $%.2f", total_price))
    }

}
