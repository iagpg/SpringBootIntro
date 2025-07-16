import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


export interface Book {
  bookId: number;
  bookName: string;
  bookAuthor: string;
}

export interface LibraryEvent {
  libraryEventId: number;
  libraryEventType: 'NEW' | 'UPDATE';
  book: Book;
}


@Injectable({
  providedIn: 'root'
})
export class LibraryAPIService {

   private apiUrl = 'http://localhost:8080/v1/libraryevent';

  constructor(private http: HttpClient) {}

    addLibraryEvent(event: LibraryEvent): Observable<LibraryEvent> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post<LibraryEvent>(this.apiUrl, event, { headers });
  }
}
