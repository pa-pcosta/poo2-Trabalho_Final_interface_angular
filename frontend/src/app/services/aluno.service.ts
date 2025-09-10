import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Aluno } from '../models/aluno.model';
import { Page } from '../models/page.model';

@Injectable({
  providedIn: 'root',
})
export class AlunoService {
  private baseUrl = 'http://localhost:8080/academico/alunos';

  constructor(private httpClient: HttpClient) {}

  getAlunos(page: number = 0, size: number = 10, search: string = ''): Observable<Page<Aluno>> {
    const params: any = { page: page.toString(), size: size.toString() };
    if (search) params.search = search;
    return this.httpClient.get<Page<Aluno>>(this.baseUrl, { params });
  }

  getAlunoById(id: number): Observable<Aluno> {
    return this.httpClient.get<Aluno>(`${this.baseUrl}/${id}`);
  }

  createAluno(aluno: Aluno) {
    return this.httpClient.post(`${this.baseUrl}`, aluno);
  }

  updateAluno(id: number, aluno: Aluno) {
    return this.httpClient.put(`${this.baseUrl}/${id}`, aluno);
  }

  deleteAluno(id: number) {
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
}
