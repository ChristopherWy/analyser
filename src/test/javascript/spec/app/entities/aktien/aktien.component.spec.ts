import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { AnalyserTestModule } from '../../../test.module';
import { AktienComponent } from 'app/entities/aktien/aktien.component';
import { AktienService } from 'app/entities/aktien/aktien.service';
import { Aktien } from 'app/shared/model/aktien.model';

describe('Component Tests', () => {
  describe('Aktien Management Component', () => {
    let comp: AktienComponent;
    let fixture: ComponentFixture<AktienComponent>;
    let service: AktienService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [AnalyserTestModule],
        declarations: [AktienComponent]
      })
        .overrideTemplate(AktienComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AktienComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AktienService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Aktien(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.aktiens && comp.aktiens[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
