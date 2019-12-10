package com.angcyo.mqttdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.angcyo.dslmqtt.DslMqtt
import com.angcyo.dslmqtt.MqttLog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dslMqtt = DslMqtt()
        dslMqtt.mqttLog = object : MqttLog() {
            override fun i(data: String?) {
                super.i(data)
                text_view.apply {
                    text = "$text\n$data"
                }
            }

            override fun e(data: String?) {
                super.e(data)
                text_view.apply {
                    text = "$text\n$data"
                }
            }
        }

        //----

        connect_button.setOnClickListener {
            dslMqtt.connect(applicationContext, url_edit_text.text.toString())
        }

        disconnect_button.setOnClickListener {
            dslMqtt.disconnect()
        }

        status_button.setOnClickListener {
            dslMqtt.mqttLog.i("isConnected:${dslMqtt.isConnected()}")
        }

        //----

        subscribe_button.setOnClickListener {
            dslMqtt.subscribe(topic_edit_text.text.toString())
        }

        unsubscribe_button.setOnClickListener {
            dslMqtt.unsubscribe(topic_edit_text.text.toString())
        }

        //----

        send_button.setOnClickListener {
            dslMqtt.publish(topic_edit_text.text.toString(), message_edit_text.text.toString())
        }

    }
}
