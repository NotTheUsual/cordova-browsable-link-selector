//
//  EventBrowserViewController.swift
//  EventBrowserViewController
//
//  Created by Elliot Stokes on 14/01/2016.
//  Copyright © 2016 Elliot Stokes. All rights reserved.
//

import UIKit
import WebKit

class EventBrowserViewController: UIViewController, WKNavigationDelegate {
    var webview: WKWebView?
    var backButton: UIBarButtonItem?
    var forwardButton: UIBarButtonItem?
    
    internal var onClosed : ((url:String?) -> Void)?

    internal var url: String? {
        didSet {
            setUrl()

        }
    }
    
    private func setUrl() {
        if let selectedUrl = self.url {
            let theUrl = NSURL(string:selectedUrl)
            let req = NSURLRequest(URL:theUrl!)
            webview?.loadRequest(req)
        }
    }
    
    @IBAction func cancelClicked(sender: AnyObject) {
        dismissViewControllerAnimated(true, completion: {
            self.onClosed?(url:nil)
        })
    }
    
    @IBAction func importClicked(sender: AnyObject) {
        dismissViewControllerAnimated(true, completion: {
            self.onClosed?(url:self.webview?.URL?.absoluteString)
        })
    }



    override func viewDidLoad() {
        super.viewDidLoad()

        webview?.allowsBackForwardNavigationGestures = true
        webview?.addObserver(self, forKeyPath: "loading", options: .New, context: nil)
                
        forwardButton = UIBarButtonItem(barButtonSystemItem: UIBarButtonSystemItem.FastForward, target: self, action: "forwardTapped")
        let spacer = UIBarButtonItem(barButtonSystemItem: .FlexibleSpace, target: nil, action: nil)
        backButton = UIBarButtonItem(barButtonSystemItem: UIBarButtonSystemItem.Rewind, target: self, action: "backTapped")
        self.toolbarItems = [backButton!, spacer, forwardButton!]
        
        
        navigationController?.toolbarHidden = false
        setUrl()
    }
    
    override func observeValueForKeyPath(keyPath: String?, ofObject object: AnyObject?, change: [String : AnyObject]?, context: UnsafeMutablePointer<Void>) {
        if keyPath == "loading" {
            if let currentWebview = webview {
                backButton?.enabled = currentWebview.canGoBack
                forwardButton?.enabled = currentWebview.canGoForward
            }
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    deinit {
        webview?.removeObserver(self, forKeyPath: "loading")
    }
    
    
    func backTapped() {
        webview?.goBack()
    }
    
    func forwardTapped() {
        webview?.goForward()
    }
    
    override func loadView() {
        webview = WKWebView()
        webview?.navigationDelegate = self
        view = webview
        
    }


}

