import { Component } from '@angular/core';
import { LibraryAPIService, LibraryEvent } from '../../services/library-api.service';
import { NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms'; // ✅ Importar o FormsModule (e não NgModel)


@Component({
  selector: 'app-library-form',
  standalone: true,
  imports: [NgIf,FormsModule],
  templateUrl: './library-form.component.html',
  styleUrl: './library-form.component.sass'
})
export class LibraryFormComponent {
  libraryEvent: LibraryEvent = {
    libraryEventId: 1,
    libraryEventType: 'NEW',
    book: {
      bookId: 1,
      bookName: '',
      bookAuthor: ''
    }
  };

errorMessage = '';
successMessage = '';


  constructor(private libraryService: LibraryAPIService) {}

  onSubmit(): void {
    this.libraryService.addLibraryEvent(this.libraryEvent).subscribe({
      next: (response) => {
        this.successMessage = 'Livro adicionado com sucesso!';
        this.errorMessage = ''; // limpa erro
        console.log('Resposta da API:', response);
      },
      error: (err) => {
        this.errorMessage = 'Erro ao adicionar livro.';
        this.successMessage = ''; // limpa sucesso
        console.error('Erro:', err);
    }
    });
  }
  
}




