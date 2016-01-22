using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Navigation;
using Microsoft.Phone.Controls;
using Microsoft.Phone.Shell;
using System.Diagnostics;
using WPCordovaClassLib.Cordova.Commands;

namespace com.ionicframework.browsertest922073.Plugins.com.megaphone.cordova.browsableLinkSelector
{
    public partial class Browser : PhoneApplicationPage
    {
        public Browser()
        {
            InitializeComponent();
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            base.OnNavigatedTo(e);

            string initialUrl = "";

            if (NavigationContext.QueryString.TryGetValue("initialURL", out initialUrl))
            {
                URL = initialUrl;
            }
            else
            {
                URL = "http://justroundup.com";
            }
        }

        protected override void OnNavigatedFrom(NavigationEventArgs e)
        {
            EventBrowser.cancel();
            base.OnNavigatedFrom(e);
        }

        protected override void OnBackKeyPress(System.ComponentModel.CancelEventArgs e)
        {
            if (WebBrowser.CanGoBack)
            {
                WebBrowser.GoBack();
                e.Cancel = true;
            }
            base.OnBackKeyPress(e);
        }

        private string _URL;
        public string URL
        {
            get
            {
                return this._URL;
            }
            set
            {
                this._URL = value;
                WebBrowser.Navigate(new Uri(value));
            }
        }

        private void BackButton_Click(object sender, EventArgs e)
        {
            WebBrowser.GoBack();
        }

        private void CancelButton_Click(object sender, EventArgs e)
        {
            this.NavigationService.GoBack();
        }

        private void ForwardButton_Click(object sender, EventArgs e)
        {
            WebBrowser.GoForward();
        }

        private void ImportButton_Click(object sender, EventArgs e)
        {
            EventBrowser.importURL(URL);
        }

        private void WebBrowser_Navigated(object sender, NavigationEventArgs e)
        {
            updateURL(e.Uri);

            ApplicationBarIconButton backButton = (ApplicationBarIconButton)ApplicationBar.Buttons[1];
            backButton.IsEnabled = WebBrowser.CanGoBack;
            ApplicationBarIconButton forwardButton = (ApplicationBarIconButton)ApplicationBar.Buttons[2];
            forwardButton.IsEnabled = WebBrowser.CanGoForward;
        }

        private void updateURL(Uri uri)
        {
            _URL = uri.ToString();
        }
    }
}
