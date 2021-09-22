#import "FlutterLaunchPlugin.h"
#if __has_include(<flutter_launch/flutter_launch-Swift.h>)
#import <flutter_launch/flutter_launch-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_launch-Swift.h"
#endif

@implementation FlutterLaunchPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterLaunchPlugin registerWithRegistrar:registrar];
}
@end
