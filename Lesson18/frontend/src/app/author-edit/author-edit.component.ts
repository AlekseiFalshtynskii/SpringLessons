import { Component, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AuthorService } from '../author/author.service';

@Component({
  selector: 'app-author-edit',
  templateUrl: './author-edit.component.html',
  styleUrls: ['./author-edit.component.scss']
})
export class AuthorEditComponent implements OnInit {
  readonly = true;
  authorForm: FormGroup;

  constructor(private location: Location,
              private activateRoute: ActivatedRoute,
              private fb: FormBuilder,
              private authorService: AuthorService) {
  }

  ngOnInit() {
    const id = this.activateRoute.snapshot.paramMap.get('id');
    if (id) {
      this.authorService.getById(id).subscribe(response => {
        this.authorForm = this.fb.group({
          id: new FormControl(response.id),
          firstName: new FormControl(response.firstName, [Validators.required]),
          lastName: new FormControl(response.lastName, [Validators.required])
        });
      });
    } else {
      this.readonly = false;
      this.authorForm = this.fb.group({
        id: new FormControl(),
        firstName: new FormControl('', [Validators.required]),
        lastName: new FormControl('', [Validators.required])
      });
    }
  }

  edit() {
    this.readonly = false;
  }

  save() {
    this.authorService.save(this.authorForm.value).subscribe(response => this.cancel());
  }

  cancel() {
    this.location.back();
  }
}
