import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
	selector: 'custom-dialog',
	templateUrl: './custom-dialog.component.html',
	styleUrls: ['./custom-dialog.component.scss']
})
export class CustomDialogComponent {

	constructor() {
	}

	@Input() display: boolean = false;
	@Input() header: string;
	@Input() disableButton: boolean = false;
	@Output() closeDialog: EventEmitter<boolean> = new EventEmitter<boolean>();
	@Output() answerForm: EventEmitter<boolean> = new EventEmitter<boolean>();

	close(): void {
		this.closeDialog.emit(false);
	}

	buttonAction(): void {
		this.answerForm.emit(true);
	}
}
