
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
        
        let storyboard = UIStoryboard(name: "EventBrowser", bundle: nil)
        let navCtrl = storyboard.instantiateViewControllerWithIdentifier("eventBrowserNavigationController") as! UINavigationController
        
        let eventBrowser = navCtrl.childViewControllers[0] as! EventBrowserViewController
        eventBrowser.onClosed = modalDidClose

        viewController!.modalTransitionStyle = .CoverVertical
        viewController!.presentViewController(navCtrl, animated: true, completion: nil)
    }
    
    func modalDidClose(url:String?) {
        let result = CDVPluginResult(status: CDVCommandStatus_OK, messageAsString: url ?? "")
        self.commandDelegate!.sendPluginResult(result, callbackId: command.callbackId)
    }

}