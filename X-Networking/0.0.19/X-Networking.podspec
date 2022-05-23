Pod::Spec.new do |spec|
    spec.name                     = 'X-Networking'
    spec.version                  = '0.0.19'
    spec.homepage                 = 'Link to the Shared Module homepage'
    spec.source                   = { :git => 'https://github.com/soramitsu/x-networking.git', :tag => '0.0.19' }
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Some description for the Shared Module'
    spec.vendored_frameworks      = 'AppCommonNetworking/commonNetworking/build/cocoapods/framework/commonNetworking.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '11.0'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':commonNetworking',
        'PRODUCT_MODULE_NAME' => 'X-Networking',
	'ENABLED_TESTABILITY' => 'NO'
    }
                
end