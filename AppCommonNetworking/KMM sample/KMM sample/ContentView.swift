//
//  ContentView.swift
//  KMM sample
//
//  Created by Ivan Shlyapkin on 04.05.2022.
//

import SwiftUI
import XNetworking

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
        // Loading data
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
