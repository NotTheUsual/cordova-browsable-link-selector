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
        public void showBrowser(string options)
        {
            Debug.WriteLine("hello");
            Deployment.Current.Dispatcher.BeginInvoke(() =>
            {
                string initialURL = "http://theverge.com";
                var frame = (Application.Current.RootVisual as TransitionFrame);
                var uri = new Uri("/Plugins/com.megaphone.cordova.browsableLinkSelector/Browser.xaml?initialURL=" + initialURL, UriKind.Relative);
                frame.Navigate(uri);
            });
        }
    }
}