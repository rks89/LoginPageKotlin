package com.rajnish.loginpagekotlin

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ProgressBar

import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.*
import androidx.room.Room
import com.rajnish.loginpagekotlin.model.Login
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        val url = URL("https://api.github.com/search/users?q=eyehunt")

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main)
        val buttonLogin= findViewById<Button>(R.id.btnLogin)
        val buttonLoginMain= findViewById<Button>(R.id.button3)

        val btnSingUp=findViewById<Button>(R.id.btnSignup)
        val imgFbLogin=findViewById<ImageView>(R.id.imageView)
        val imgGoogle= findViewById<ImageView>(R.id.imageView2)
        val imgTwitter= findViewById<ImageView>(R.id.imageView3)

        //login information
        val editEmail=findViewById<EditText>(R.id.editEmail)
        val editPassword=findViewById<EditText>(R.id.editPassword)
        //val context = InstrumentationRegistry.getContext()



        buttonLoginMain.setOnClickListener {


            //val email=editEmail.text.toString()

            //check if the EditText have values or not

            if (editEmail.text.toString().trim().isEmpty()) {
                editEmail.error = "Required"
                Toast.makeText(applicationContext, "Email Required ", Toast.LENGTH_SHORT).show()
            } else if (editPassword.text.toString().trim().isEmpty()) {
                editPassword.error = "Required"
                Toast.makeText(applicationContext, "Password Required ", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(applicationContext, "Login Successful ", Toast.LENGTH_SHORT).show()
                // After successful login u will called the login method
                //login(applicationContext)

                //val objTaskLogin = LoginTask()
               // objTaskLogin.execute(url)
            }
        }}

        }
// AsyncTask inner class
class LoginTask : AsyncTask<URL, Int, String>() {

    public var result: String = "";

    override fun onPreExecute() {
        super.onPreExecute()
        //progressBar.visibility = View.VISIBLE
    }

    override fun doInBackground(vararg params: URL?): String
    {

        val connect = params[0]?.openConnection() as HttpURLConnection
        connect.readTimeout = 8000
        connect.connectTimeout = 8000
        connect.requestMethod = "GET"
        connect.connect();


        val responseCode: Int = connect.responseCode;
        if (responseCode == 200) {
            result = streamToString(connect.inputStream)
        }
        return result
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
       // progressBar.visibility = View.GONE
        //set result in textView
        println("JsonValue $result)");

       // var objLogin=Login()
        //objLogin.parseJason(result.toString())
    }
}

fun streamToString(inputStream: InputStream): String {

    val bufferReader = BufferedReader(InputStreamReader(inputStream))
    var line: String
    var result = ""
    try
    {
        do {
            line = bufferReader.readLine()
            if (line != null) {
                result += line
            }
        }
        while (line != null)
        inputStream.close()
    }

    catch (ex: Exception) {
        println(ex.toString())
    }
    return result
}