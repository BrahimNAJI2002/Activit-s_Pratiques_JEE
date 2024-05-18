import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";
import {Observable} from "rxjs";

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  //products! :Observable<Array<Product>>;
  public products : Array<Product> = [];
  public keyword : string = "";

  constructor(private productService:ProductService) {
  }

  ngOnInit(){
    this.getProducts();
  }

  getProducts(){
    this.productService.getProduct()
      .subscribe({
        next : data => {
          this.products=data
        },
        error : err => {
          console.log(err);
        }
      })
    //this.products$=this.productService.getProduct()
  }

  handleCheckProduct(product: Product) {
    this.productService.checkProduct(product).subscribe({
      next : updateProduct => {
        product.checked =!product.checked;
        //this.getProducts()
      }
    })
  }

  handleDelete(product: Product) {
    if (confirm("Etes vous sure ?"))
    this.productService.deleteProduct(product).subscribe({
      next:value => {
        //this.getProducts()
        this.products = this.products.filter(p=>p.id!=product.id);
      }
    })
  }

  searchProducts() {
    this.productService.searchProducts(this.keyword).subscribe({
      next:value => {
        this.products = value;
      }
    })
  }
}
