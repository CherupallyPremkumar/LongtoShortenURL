import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Backendapi } from 'src/Backendapi.service';
import { Url } from 'src/Url'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
  export class AppComponent  {

    constructor(private backend:Backendapi)
    {}

    url: Url = new Url();
    url2='';
    shortUrl: string = '';
    errorMessage: string = '';

    generateShortUrl() {
      console.log('url2 value:', this.url2);
      this.url.longUrl=this.url2;
      console.log('url2 value:', this.url.longUrl);
      if (!this.url.longUrl.startsWith('http://') && !this.url.longUrl.startsWith('https://')) {
        this.errorMessage = 'Invalid URL structure. Make sure it starts with http:// or https://';
        this.shortUrl = '';
      } else {
        this.errorMessage = '';

        this.backend.postLink(this.url).subscribe(data=>this.shortUrl=data.longUrl)
        setTimeout(() => {
          this.shortUrl = '';
        }, 300000);
      }
    }
}
