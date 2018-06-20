import Flutter
import UIKit
    
public class SwiftFlutterLaunchPlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let channel = FlutterMethodChannel(name: "flutter_launch", binaryMessenger: registrar.messenger())
    let instance = SwiftFlutterLaunchPlugin()
    registrar.addMethodCallDelegate(instance, channel: channel)
  }

  public func handle(_ call: FlutterMethodCall, result: @escaping FlutterResult) {
    if ("launchWathsApp" == call.method) {
      let args = call.arguments as! Dictionary<String, String>
      let phone = args["phone"]
      let message = args["message"]

      let urlString = "https://api.whatsapp.com/send?phone=\(phone ?? "0")&message=\(message ?? "0")"

      let urlStringEncoded = urlString.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)
      let URL = NSURL(string: urlStringEncoded!)

      if UIApplication.shared.canOpenURL(URL! as URL) {
        UIApplication.shared.openURL(URL! as URL)
      }
    }
  }
}
