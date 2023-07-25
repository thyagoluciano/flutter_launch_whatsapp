package br.com.thyagoluciano.flutter_launch

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.net.URLEncoder


/** FlutterLaunchPlugin */
class FlutterLaunchPlugin: FlutterPlugin, MethodCallHandler {
  private lateinit var channel : MethodChannel
  private lateinit var context: Context

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "br.com.thyagoluciano/flutter_launch")
    channel.setMethodCallHandler(this)
    context = flutterPluginBinding.applicationContext

  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    try {
      val pm: PackageManager = context.packageManager

      if (call.method == "launchWhatsapp") {

        try {

          val phone: String? = call.argument("phone")
          val message: String? = call.argument("message")

          val url: String = "https://api.whatsapp.com/send?phone=$phone&text=${URLEncoder.encode(message, "UTF-8")}"

          if (appInstalledOrNot("com.whatsapp")) {
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.setPackage("com.whatsapp")
            intent.data = Uri.parse(url)

            if (intent.resolveActivity(pm) != null) {
              context.startActivity(intent)
            }
          } else if (appInstalledOrNot("com.whatsapp.wb4")) {
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.setPackage("com.whatsapp.wb4")
            intent.data = Uri.parse(url)

            if (intent.resolveActivity(pm) != null) {
              context.startActivity(intent)
            }
          }
         } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(context, "No app was found with the application ID ${call.argument<String>("applicationId")}\nOpening Google Play...", Toast.LENGTH_LONG).show()
            result.error("APP_NOT_FOUND", "No app was found with the specified application ID", "Please specify a correct application ID");

            val i = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp"))
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(i)
        }
      }

      if (call.method == "hasApp") {
        val app: String? = call.argument("name")

        when(app) {
          "whatsapp" -> result.success(appInstalledOrNot("com.whatsapp"))
          "whatsapp.wb4" -> result.success(appInstalledOrNot("com.whatsapp.wb4"))
          else -> {
            result.error("App not found", "", null)
          }
        }
      }
    } catch (e: PackageManager.NameNotFoundException) {
      result.notImplemented()
    }
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }

  private fun appInstalledOrNot(uri: String) : Boolean {
    val pm: PackageManager = context.packageManager

    var appInstalled: Boolean = try {
      pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
      true
    } catch (e: PackageManager.NameNotFoundException) {
      false
    }

    return appInstalled
  }
}
