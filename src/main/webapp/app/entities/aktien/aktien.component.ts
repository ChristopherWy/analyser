import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAktien } from 'app/shared/model/aktien.model';
import { AktienService } from './aktien.service';
import { AktienDeleteDialogComponent } from './aktien-delete-dialog.component';

@Component({
  selector: 'jhi-aktien',
  templateUrl: './aktien.component.html'
})
export class AktienComponent implements OnInit, OnDestroy {
  aktiens?: IAktien[];
  eventSubscriber?: Subscription;

  constructor(protected aktienService: AktienService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.aktienService.query().subscribe((res: HttpResponse<IAktien[]>) => (this.aktiens = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAktiens();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAktien): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAktiens(): void {
    this.eventSubscriber = this.eventManager.subscribe('aktienListModification', () => this.loadAll());
  }

  delete(aktien: IAktien): void {
    const modalRef = this.modalService.open(AktienDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.aktien = aktien;
  }
}
