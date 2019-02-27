import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Author, AuthorService } from '../author/author.service';
import { ErrorComponent } from '../error/error.component';

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.scss']
})
export class AuthorsComponent implements OnInit {
  authors: Array<Author>;
  displayedColumns: string[] = ['id', 'firstName', 'lastName', 'action'];

  constructor(private authorService: AuthorService,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.authorService.getAll().subscribe(response => {
      this.authors = response;
    });
  }

  deleteById(id: string) {
    this.authorService.deleteById(id).subscribe(
      response => {
        this.getAll();
      },
      error => {
        this.dialog.open(ErrorComponent, {
          data: {
            error: error.error
          }
        });
      }
    );
  }

  deleteAll() {
    this.authorService.deleteAll().subscribe(
      response => {
        this.getAll();
      },
      error => {
        this.dialog.open(ErrorComponent, {
          data: {
            error: error.error
          }
        });
      }
    );
  }
}
