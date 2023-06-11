import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BidStatus } from 'src/app/enums/bidStatusEnums';
import { BidApiService } from 'src/app/user/service/bid-api.service';
import { ProjectApiService } from 'src/app/user/service/project-api.service';

@Component({
  selector: 'fl-bid',
  templateUrl: './bid.component.html',
  styleUrls: ['./bid.component.css'],
})
export class BidComponent {
  userId: number = 0;
  selectedStatus: String = BidStatus.PENDING;

  constructor(private bidApiService: BidApiService, private router: Router) {
    this.userId = Number(localStorage.getItem('userId'));
  }

  bids: any;

  async ngOnInit(): Promise<void> {
    this.bids = await this.bidApiService.getBids({
      freelancerId: this.userId,
      status: this.selectedStatus,
    });
  }
}
