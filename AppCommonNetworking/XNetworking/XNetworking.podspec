Pod::Spec.new do |spec|
    spec.name                     = 'XNetworking'
    spec.version                  = '0.0.57'
    spec.homepage                 = 'Link to the Shared Module homepage'
    spec.source                   = { :git => 'https://github.com/soramitsu/x-networking.git', :tag => '0.0.57' }
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Some description for the Shared Module'
    spec.vendored_frameworks      = 'AppCommonNetworking/XNetworking/build/XCFrameworks/release/XNetworking.xcframework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '11.0'


    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':XNetworking',
        'PRODUCT_MODULE_NAME' => 'XNetworking',
    }

end