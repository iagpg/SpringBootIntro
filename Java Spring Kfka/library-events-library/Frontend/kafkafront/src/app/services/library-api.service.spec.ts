import { TestBed } from '@angular/core/testing';

import { LibraryAPIService } from './library-api.service';

describe('LibraryAPIService', () => {
  let service: LibraryAPIService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LibraryAPIService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
