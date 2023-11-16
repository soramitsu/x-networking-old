Pod::Spec.new do |spec|
    spec.name                     = 'XNetworking'
    spec.version                  = '0.2.5-temp3'
    spec.homepage                 = 'Link to the Shared Module homepage'
    spec.source                   = { :git => 'https://github.com/soramitsu/x-networking.git', :tag => '0.2.5-temp3' }
    spec.authors                  = ''
    spec.license                  = ''
    spec.summary                  = 'Some description for the Shared Module'
    spec.vendored_frameworks      = 'XNetworking_fearlesswallet.framework'
    spec.libraries                = 'c++'
    spec.ios.deployment_target = '14.1'
                
                
    spec.pod_target_xcconfig = {
        'KOTLIN_PROJECT_PATH' => ':lib:fearlesswallet',
        'PRODUCT_MODULE_NAME' => 'XNetworking_fearlesswallet',
    }

end