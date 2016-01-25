Browsable Link Selector
======================

Will bring up a webview which allows you to browse using a webview and select a url which will be returned in the callback

to install

    cordova plugins add cordova-plugin-browsable-link-selector


## Windows Phone

To get transitions between your app and the browser, you'll need to add the WPToolkit from nuget, and then, in your `App.xaml.cs` file, change
```
RootFrame = new PhoneApplicationFrame();
```
to
```
RootFrame = new TransitionFrame();
```