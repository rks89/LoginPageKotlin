package com.rajnish.loginpagekotlin.model

import org.json.JSONArray
import org.json.JSONObject


class Login{
    var node_id:String =""
    var url:String =""
    var followers_url=""

    fun parseJason(jsonString:String):Login
    {
        var objLogin= Login()

        var jsonObject=JSONObject(jsonString)
        var jsonObject1 =JSONObject()
        //val jsonArray= JSONArray()

        val jsonArray=jsonObject.getJSONArray("items")
        //println("Final Json value$jsonObject3.toString()")

        var count=0
        for(i in 0 until jsonArray.length())
        {

            val jsonObject3 = jsonArray.getJSONObject(0)
            //println(jsonArray)
            //objLogin.node_id= jsonObject.getString("score")
            //objLogin.url=jsonObject.get("url").toString()
            //objLogin.followers_url=jsonObject.get("followers_url").toString()
            println(jsonObject3.getString("score"))
            count++

        }
        return objLogin
    }
}