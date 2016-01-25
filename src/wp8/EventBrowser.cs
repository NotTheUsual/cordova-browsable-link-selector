using Microsoft.Phone.Controls;
using System;
using System.Diagnostics;
using System.Windows;
using System.Windows.Navigation;
using System.Windows.Threading;

namespace WPCordovaClassLib.Cordova.Commands
{
    public class EventBrowser : BaseCommand
    {
        public static EventBrowser Responder { get; set; }

        public void showBrowser(string options)
        {
            EventBrowser.Responder = this;
            Deployment.Current.Dispatcher.BeginInvoke(() =>
            {
                string initialURL = JSON.JsonHelper.Deserialize<String[]>(options)[0];
                var frame = (Application.Current.RootVisual as TransitionFrame);
                var uri = new Uri("/Plugins/com.megaphone.cordova.browsableLinkSelector/Browser.xaml?initialURL=" + initialURL, UriKind.Relative);
                frame.Navigate(uri);
            });
        }

        public static void importURL(string URL)
        {
            Responder.DispatchCommandResult(new PluginResult(PluginResult.Status.OK, URL));
        }

        public static void cancel()
        {
            Responder.DispatchCommandResult(new PluginResult(PluginResult.Status.NO_RESULT));
        }
    }
}