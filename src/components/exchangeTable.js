import React, { useMemo } from 'react'
import { useTable } from 'react-table'
import MOCK_DATA from './exchangeRate.json'
import { ExchangeRateColumns } from './exchangeColumns'
import './table.css'

export const exchangeTable = () => {

    //const columns = useMemo(() => ExchangeRate, []);
    //const data = useMemo(() => MOCK_DATA, []);

    const tableInstance = useTable({
        columns : ExchangeRateColumns,
        data: MOCK_DATA
    })

    const { getTableProps, 
        getTableBodyProps, 
        headerGroup, 
        rows, 
        prepareRows,
    } = tableInstance

  return (
    <table {...getTableProps()}>
        <thead>
            {
                headerGroup.map((headerGroup) => (
                    <tr {... headerGroup.getHeaderGroupProps()}>
                        {
                            headerGroup.headers.map((columns) => (
                                <th {...columns.getHeadrProps()}>
                                    {
                                        columns.render('Header')
                                    }
                                </th>
                            ))
                        }
                    </tr>
            ))}
            
        </thead>
        <tbody {...getTableBodyProps()}>
            {
                rows.map(rows => {
                    prepareRows(rows)
                })
            }
            <tr {...rows.getRowProps()}>
                {
                    rows.cells.map( cell => {
                        return <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
                    })
                }
            </tr>
        </tbody>
    </table>
  )
}

export default exchangeTable
