import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiResponse } from 'src/app/shared/model/apiResponse';
import { BidApiService } from 'src/app/user/service/bid-api.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'fl-user-bids',
  templateUrl: './user-bids.component.html',
  styleUrls: ['./user-bids.component.css'],
})
export class UserBidsComponent {
  @Input('projectId') projectId = 0;

  constructor(
    private bidApiService: BidApiService,
    private route: ActivatedRoute,
    private router: Router
  ) {
    this.route.params.subscribe((params) => {
      this.projectId = params['projectId'];
    });
  }

  bids: any;
  freelancerId!: number;
  status!: ApiResponse;

  async ngOnInit(): Promise<void> {
    this.bids = await this.bidApiService.getBids({
      projectId: this.projectId,
    });
    console.log(this.bids[0].freelancerDetails.company);
  }

  approveBid(bidId: number) {
    Swal.fire({
      title: 'Approve this bid?',
      text: 'All other bids on this project will be rejected.',
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#14b8a6',
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
    }).then((result) => {
      if (result.value) {
        this.sendApproveBidRequest(bidId);
        Swal.fire({
          title: 'Approved',
          text: 'You can see this project in "Work in progress" section',
          icon: 'success',
          confirmButtonColor: '#14b8a6',
          confirmButtonText: 'Ok',
        }).then(() => {
          this.router.navigateByUrl('/home/myProjects/client');
        });
      }
    });
  }

  async sendApproveBidRequest(bidId: number): Promise<void> {
    this.status = await this.bidApiService.updateBidStatusToApprove({
      bidId: bidId,
      projectId: this.projectId,
    });
  }
}
