import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BooksComponent } from './books/books.component';
import { BookEditComponent } from './book-edit/book-edit.component';
import { AuthorsComponent } from './authors/authors.component';
import { AuthorEditComponent } from './author-edit/author-edit.component';
import { GenresComponent } from './genres/genres.component';
import { GenreEditComponent } from './genre-edit/genre-edit.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

const routes: Routes = [
  { path: '', component: BooksComponent },
  { path: 'books', component: BooksComponent },
  { path: 'books/edit/:id', component: BookEditComponent },
  { path: 'books/add', component: BookEditComponent },
  { path: 'authors', component: AuthorsComponent },
  { path: 'authors/add', component: AuthorEditComponent },
  { path: 'authors/edit/:id', component: AuthorEditComponent },
  { path: 'genres', component: GenresComponent },
  { path: 'genres/add', component: GenreEditComponent },
  { path: 'genres/edit/:id', component: GenreEditComponent },
  { path: '**', component: PageNotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
