import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { BookService } from '../book/book.service';
import { Author, AuthorService } from '../author/author.service';
import { Genre, GenreService } from '../genre/genre.service';

@Component({
  selector: 'app-book-edit',
  templateUrl: './book-edit.component.html',
  styleUrls: ['./book-edit.component.scss']
})
export class BookEditComponent implements OnInit {
  readonly = true;
  bookForm: FormGroup;
  authors: Array<Author>;
  genres: Array<Genre>;

  constructor(private location: Location,
              private activateRoute: ActivatedRoute,
              private fb: FormBuilder,
              private bookService: BookService,
              private authorService: AuthorService,
              private genreService: GenreService) {
  }

  ngOnInit() {
    const id = Number(this.activateRoute.snapshot.paramMap.get('id'));
    if (id) {
      this.bookService.getById(id).subscribe(response => {
        this.bookForm = this.fb.group({
          id: new FormControl(response.id),
          name: new FormControl(response.name, [Validators.required]),
          description: new FormControl(response.description),
          authors: new FormControl(response.authors, [Validators.required]),
          genres: new FormControl(response.genres, [Validators.required])
        });
      });
    } else {
      this.readonly = false;
      this.bookForm = this.fb.group({
        id: new FormControl(),
        name: new FormControl('', [Validators.required]),
        description: new FormControl(''),
        authors: new FormControl([], [Validators.required]),
        genres: new FormControl([], [Validators.required])
      });
    }
    this.authorService.getAll().subscribe(response => {
      this.authors = response;
    });
    this.genreService.getAll().subscribe(response => this.genres = response);
  }

  edit() {
    this.readonly = false;
  }

  save() {
    console.log(JSON.stringify(this.bookForm.value));
    this.bookService.save(this.bookForm.value).subscribe(response => this.cancel());
  }

  cancel() {
    this.location.back();
  }

  compare(obj1, obj2): boolean {
    return obj1.id === obj2.id;
  }
}
