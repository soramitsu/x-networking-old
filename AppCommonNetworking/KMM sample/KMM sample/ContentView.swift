//
//  ContentView.swift
//  KMM sample
//
//  Created by Ivan Shlyapkin on 04.05.2022.
//

import SwiftUI
import comm

struct ContentView: View {
    var body: some View {
        VStack {
            Text("Hello, world!")
                .padding()
            Text("Hello, world!")
                .padding()
            Text("Hello, world!")
                .padding()
            Text("Hello, world!")
                .padding()
            Button("test") {
                load()
            }
        }
        
    }
    
    func load() {
//        let provider = SoraHttpClientProviderImpl()
//        var soraNetworkClient = SoraNetworkClient(timeout: 60000, logging: true, provider: provider)
//
//        var fearlessChainsBuilder = FearlessChainsBuilder(networkClient: soraNetworkClient,
//                                                          baseUrl: "https://raw.githubusercontent.com/arvifox/arvifoxandroid/develop/felete/")
//        var hi = SubQueryClient(networkClient: soraNetworkClient,
//                                baseUrl: "https://api.subquery.network/sq/sora-xor/sora-dev")
//        hi.getReferrerRewards(address: "cnVkoGs3rEMqLqY27c2nfVXJRGdzNJk2ns78DcqtppaSRe8qm", blockHeight: 0, base: 18) { result, error in
//            print(result?.rewards)
//        }
    }
    
            
            
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
