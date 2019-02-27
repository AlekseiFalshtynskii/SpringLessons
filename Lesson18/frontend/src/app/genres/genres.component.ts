import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Genre, GenreService } from '../genre/genre.service';
import { ErrorComponent } from '../error/error.component';

@Component({
  selector: 'app-genres',
  templateUrl: './genres.component.html',
  styleUrls: ['./genres.component.scss']
})
export class GenresComponent implements OnInit {
  genres: Array<Genre>;
  displayedColumns: string[] = ['id', 'name', 'action'];

  constructor(private genreService: GenreService,
              private dialog: MatDialog) { }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.genreService.getAll().subscribe(response => {
      this.genres = response;
    });
  }

  deleteById(id: string) {
    this.genreService.deleteById(id).subscribe(
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
    this.genreService.deleteAll().subscribe(
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
