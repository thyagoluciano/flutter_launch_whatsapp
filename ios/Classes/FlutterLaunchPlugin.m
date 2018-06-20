#import "FlutterLaunchPlugin.h"
#import <flutter_launch/flutter_launch-Swift.h>

@implementation FlutterLaunchPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterLaunchPlugin registerWithRegistrar:registrar];
}
@end
