package nl.avisi.structurizr.site.generatr.site.views

import kotlinx.html.*
import nl.avisi.structurizr.site.generatr.site.model.TableViewModel

fun FlowContent.table(viewModel: TableViewModel) {
    table {
        thead {
            viewModel.headerRows.forEach {
                row(it)
            }
        }
        tbody {
            viewModel.bodyRows.forEach {
                row(it)
            }
        }
    }
}

private fun THEAD.row(viewModel: TableViewModel.RowViewModel) {
    tr {
        viewModel.columns.forEach {
            cell(it)
        }
    }
}

private fun TBODY.row(viewModel: TableViewModel.RowViewModel) {
    tr {
        viewModel.columns.forEach {
            cell(it)
        }
    }
}

private fun TR.cell(viewModel: TableViewModel.CellViewModel) {
    when (viewModel) {
        is TableViewModel.TextCellViewModel -> td { +viewModel.title }
        is TableViewModel.LinkCellViewModel -> td { link(viewModel.link) }
    }
}
