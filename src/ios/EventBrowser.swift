
//
//  EventBrowser.swift
//  EventBrowser
//
//  Created by Elliot Stokes on 14/01/2016.
//  Copyright © 2016 Elliot Stokes. All rights reserved.
//

import UIKit

@objc(EventBrowser) class EventBrowser: CDVPlugin {

    var command = CDVInvokedUrlCommand()

    func showBrowser(command: CDVInvokedUrlCommand) {
        self.command = command
        var url = "http://www.google.com"
        if let defaultUrl = command.argumentAtIndex(0) {
            url = defaultUrl as! String
        }

        let storyboard = UIStoryboard(name: "EventBrowser", bundle: nil)
        let navCtrl = storyboard.instantiateViewControllerWithIdentifier("eventBrowserNavigationController") as! UINavigationController
        
        let eventBrowser = navCtrl.childViewControllers[0] as! EventBrowserViewController
        
        eventBrowser.url = url
        eventBrowser.onClosed = modalDidClose

        viewController!.modalTransitionStyle = .CoverVertical
        viewController!.presentViewController(navCtrl, animated: true, completion: nil)
        
   
    }
    
    func modalDidClose(url:String?) {
        let result = CDVPluginResult(status: CDVCommandStatus_OK, messageAsString: url ?? "")
        self.commandDelegate!.sendPluginResult(result, callbackId: command.callbackId)
    }

}