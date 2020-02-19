package sa.ksu.gpa.saleem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_add_food.*
import kotlinx.android.synthetic.main.activity_add_food.view.*

class AddFoodActivity : AppCompatActivity() {
    lateinit var adapter :ItemAdapter
    var listdata  = ArrayList<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)
        toolbar.title = "اضافة وجبة مفصلة"

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        val recyclerView: RecyclerView = findViewById(R.id.rv_component)






        add.setOnClickListener {
            var item = Item()
            item.name = name.text.toString()
            item.amount = amount.text.toString()
            item.weight = wight.text.toString()
            listdata.add(item)
            recyclerView.layoutManager = LinearLayoutManager(this)
            adapter =ItemAdapter(listdata)
            recyclerView.adapter = adapter

            adapter.notifyDataSetChanged()
            Log.e("hhh","list.size ==> "+listdata.size)
            name.setText("")
            amount.setText("")
            wight.setText("")
        }


    }
    inner class ViewHolder internal constructor(
        inflater: LayoutInflater,
        parent: ViewGroup?
    ) : RecyclerView.ViewHolder(
        inflater.inflate(
            R.layout.item_component,
            parent,
            false
        )
    ) {
        val name: TextView
        val amount: TextView
        val weight: TextView


        init { // TODO: Customize the item layout
            name = itemView.findViewById<View>(R.id.tvName) as TextView
            amount = itemView.findViewById(R.id.tvAmount)as TextView
            weight = itemView.findViewById(R.id.tvWeight)as TextView

        }
    }
    inner class ItemAdapter(var list: ArrayList<Item>) :
        RecyclerView.Adapter<ViewHolder>() {

        private val mItemCount: Int
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context),
                parent
            )
        }
        fun addItem(item:Item){
            list.add(item)
            Log.e("hhh","hhh === === >> list size"+list.size)
            notifyDataSetChanged()
        }
        override fun onBindViewHolder(
            holder: ViewHolder,
            position: Int
        ) { //            holder.text.setText(String.valueOf(position));
            holder.name.text = list[position].name
            holder.amount.text = list[position].amount
            holder.weight.text = list[position].weight

        }

        override fun getItemCount(): Int {
            return mItemCount
        }

        init {
            mItemCount = list.size
        }
    }

    inner class Item{
        lateinit var name : String
        lateinit var amount : String
        lateinit var weight : String

    }
}
