import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { GenreService } from '../genre/genre.service';

@Component({
  selector: 'app-genre-edit',
  templateUrl: './genre-edit.component.html',
  styleUrls: ['./genre-edit.component.scss']
})
export class GenreEditComponent implements OnInit {
  readonly = true;
  genreForm: FormGroup;

  constructor(private location: Location,
              private activateRoute: ActivatedRoute,
              private fb: FormBuilder,
              private genreService: GenreService) {
  }

  ngOnInit() {
    const id = Number(this.activateRoute.snapshot.paramMap.get('id'));
    if (id) {
      this.genreService.getById(id).subscribe(response => {
        this.genreForm = this.fb.group({
          id: new FormControl(response.id),
          name: new FormControl(response.name, [Validators.required])
        });
      });
    } else {
      this.readonly = false;
      this.genreForm = this.fb.group({
        id: new FormControl(),
        name: new FormControl('', [Validators.required])
      });
    }
  }

  edit() {
    this.readonly = false;
  }

  save() {
    this.genreService.save(this.genreForm.value).subscribe(response => this.cancel());
  }

  cancel() {
    this.location.back();
  }
}
