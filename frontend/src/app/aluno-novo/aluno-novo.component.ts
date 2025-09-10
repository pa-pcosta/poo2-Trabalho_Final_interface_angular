import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatIcon } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatDialog } from '@angular/material/dialog';

import { Aluno } from '../models/aluno.model';
import { AlunoService } from '../services/aluno.service';
import { ConfirmDialogComponent, ConfirmDialogData } from '../confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-aluno-novo',
  standalone: true,
  imports: [
    FormsModule,
    MatFormField,
    MatInput,
    MatLabel,
    MatIcon,
    MatCardModule,
    MatDividerModule,
  ],
  templateUrl: './aluno-novo.component.html',
  styleUrls: ['./aluno-novo.component.css']
})
export class AlunoNovoComponent {
  aluno: Aluno = {} as Aluno;

  constructor(private alunoService: AlunoService, private router: Router, private dialog: MatDialog) {}

  inserirAluno() {
    const dialogData: ConfirmDialogData = {
      title: 'Confirmar inclusão',
      message: `Tem certeza que deseja incluir o aluno "${this.aluno.nome}"?`,
      confirmText: 'Sim, incluir',
      cancelText: 'Cancelar',
      confirmColor: 'secondary'
    };

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px',
      data: dialogData
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.alunoService.createAluno(this.aluno).subscribe({
          next: () => this.router.navigate(['/aluno']),
          error: err => console.error('Erro ao cadastrar aluno:', err)
        });
      }
    });
  }

  cancelar() {
    this.router.navigate(['/aluno']);
  }
}
