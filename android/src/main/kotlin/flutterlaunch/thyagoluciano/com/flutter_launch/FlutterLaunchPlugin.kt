package flutterlaunch.thyagoluciano.com.flutter_launch

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.net.URLEncoder

class FlutterLaunchPlugin(val mRegistrar: Registrar): MethodCallHandler {

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "flutter_launch")
      channel.setMethodCallHandler(FlutterLaunchPlugin(registrar))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result): Unit {
    try {
      val context: Context = mRegistrar.context()
      val pm: PackageManager = context.packageManager

      if (call.method.equals("launchWathsApp")) {

        val phone: String? = call.argument("phone")
        val message: String? = call.argument("message")

        val url: String = "https://api.whatsapp.com/send?phone=$phone&text=${URLEncoder.encode(message, "UTF-8")}"

        if (appInstalledOrNot("com.whatsapp")) {
          val intent: Intent = Intent(Intent.ACTION_VIEW)
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
          intent.setPackage("com.whatsapp")
          intent.setData(Uri.parse(url))

          if (intent.resolveActivity(pm) != null) {
            context.startActivity(intent)
          }
        }
      }

      if (call.method.equals("hasApp")) {
        val app: String? = call.argument("name");

        when(app) {
          "facebook" -> result.success(appInstalledOrNot("com.facebook.katana"))
          "whatsapp" -> result.success(appInstalledOrNot("com.whatsapp"))
          else -> {
            result.error("App not found", "", null)
          }
        }
      }
    } catch (e: PackageManager.NameNotFoundException) {
      result.error("Name not found", e.message, null)
    }
  }

  private fun appInstalledOrNot(uri: String) : Boolean {
    val context: Context = mRegistrar.context();
    val pm: PackageManager = context.packageManager
    var appInstalled: Boolean

    try {
      pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES)
      appInstalled = true
    } catch (e: PackageManager.NameNotFoundException) {
      appInstalled = false
    }

    return appInstalled
  }
}
